package fr.training.spring.Library.batch.exportjob;

import fr.training.spring.Library.batch.common.FullReportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class ExportJobConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ExportJobConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LibraryProcessor libraryProcessor;

    @Autowired
    private FullReportListener fullReportListener;



    @Bean(name ="exportJob")
    public Job exportLibraryJob(final Step exportStep, final Step exportStepBook){
        return jobBuilderFactory.get("export-job")
                .incrementer(new RunIdIncrementer())
                .start(exportStep)
                .next(exportStepBook)
                .listener(fullReportListener)
                .build();
    }

    @Bean
    public Step exportStep(final ItemWriter<LibraryBatchDTO> exportWriter) {
        //Exporter les library
        // on utilise le LibraryService avec findLibraryById, donc on a besoin processor pour mapper les données
        return stepBuilderFactory.get("export-step")
                .<String, LibraryBatchDTO>chunk(5)
                .processor(libraryProcessor)  //Mapper vers LibraryBatchDTO
                .writer(exportWriter)
                .reader(exportReader())
                .build();
    }

    @Bean
    public Step exportStepBook(final ItemWriter<BookLibraryDTO> exportWriterBook) {
        //exporter les book et library associé directement par la requetes SQL
        return stepBuilderFactory.get("export-step")
                .<BookLibraryDTO, BookLibraryDTO>chunk(5)
                .writer(exportWriterBook)
                .reader(exportReaderBook())
                .build();
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<LibraryBatchDTO> exportWriter(@Value("#{jobParameters['output-file']}") final String outputFile) {
        final FlatFileItemWriter<LibraryBatchDTO> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(outputFile));

        // séparateur csv
        final DelimitedLineAggregator<LibraryBatchDTO> lineAggregator = new DelimitedLineAggregator<LibraryBatchDTO>();
        lineAggregator.setDelimiter(";");  //mettre ; comme séparateur de csv

        //extraire les champs du DTO dans des chaines de caractères
        final BeanWrapperFieldExtractor<LibraryBatchDTO> fieldExtractor = new BeanWrapperFieldExtractor<LibraryBatchDTO>();
        fieldExtractor.setNames(new String[] {"idLibrary","addressCity", "addressNumber","addressPostalCode","addressStreet","libraryDirectorNom","libraryDirectorPrenom","libraryType"});  //extraire les champs



        lineAggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(lineAggregator);
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(final Writer writer) throws IOException {
                writer.write("id;ville;numero;cp;street;nom;prenom;type");
            }
        });
        return writer;
    }


    @StepScope
    @Bean
    public FlatFileItemWriter<BookLibraryDTO> exportWriterBook(@Value("#{jobParameters['output-file-book']}") final String outputFile) {
        //final String outputFile = "BookLibrary.csv";
        final FlatFileItemWriter<BookLibraryDTO> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(outputFile));

        // séparateur csv
        final DelimitedLineAggregator<BookLibraryDTO> lineAggregator = new DelimitedLineAggregator<BookLibraryDTO>();
        lineAggregator.setDelimiter(";");  //mettre ; comme séparateur de csv

        //extraire les champs du DTO dans des chaines de caractères
        final BeanWrapperFieldExtractor<BookLibraryDTO> fieldExtractor = new BeanWrapperFieldExtractor<BookLibraryDTO>();
        fieldExtractor.setNames(new String[] {"author", "title", "isbn", "genre", "page", "numerADR", "street", "city","cp", "idLibrary"});  // les champs de DTO



        lineAggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(lineAggregator);
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(final Writer writer) throws IOException {
                //enete csv
                writer.write("AUTHOR;TITLE;ISBN;GENRE;NOMBER_PAGE;ADDRESS_NUMBER;ADDRESS_STREET;ADDRESS_CITY;ADDRESS_POSTAL_CODE;ID_LIBRARY");
            }
        });
        return writer;
    }


    @Bean
    public JdbcCursorItemReader<String> exportReader() {
        final JdbcCursorItemReader<String> reader = new JdbcCursorItemReader<String>();
        reader.setDataSource(dataSource);
        reader.setSql("select ID_LIBRARY FROM LIBRARY");
        reader.setRowMapper(new SingleColumnRowMapper<String>());
        return reader;
    }


    @Bean
    public JdbcCursorItemReader<BookLibraryDTO> exportReaderBook() {
        //En retour de requete SQL,
        //on les
        final JdbcCursorItemReader<BookLibraryDTO> reader = new JdbcCursorItemReader<BookLibraryDTO>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT b.AUTHOR, b.TITLE, b.ISBN, b.GENRE, b.NOMBER_PAGE, \n" +
                " l.ADDRESS_NUMBER, ADDRESS_STREET, ADDRESS_CITY,ADDRESS_POSTAL_CODE, ID_LIBRARY\n" +
                "FROM LIBRARY as l, BOOK as b, LIBRARY_BOOKS as lb\n" +
                "where b.ID_BOOK = lb.BOOKS_ID_BOOK\n" +
                "  and lb.LIBRARYJPA_ID_LIBRARY = l.ID_LIBRARY");
        reader.setRowMapper(new BookRowMapper());
        return reader;
    }

    public class BookRowMapper implements RowMapper<BookLibraryDTO> {

        @Override
        public BookLibraryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            final BookLibraryDTO bookLibraryDTO = new BookLibraryDTO();
            bookLibraryDTO.setAuthor(rs.getString("AUTHOR"));
            bookLibraryDTO.setTitle(rs.getString("TITLE"));
            bookLibraryDTO.setIsbn(rs.getString("ISBN"));
            bookLibraryDTO.setGenre(rs.getString("GENRE"));
            bookLibraryDTO.setPage(rs.getInt("NOMBER_PAGE"));
            bookLibraryDTO.setNumerADR(rs.getInt("ADDRESS_NUMBER"));
            bookLibraryDTO.setStreet(rs.getString("ADDRESS_STREET"));
            bookLibraryDTO.setCity(rs.getString("ADDRESS_CITY"));
            bookLibraryDTO.setCp(rs.getInt("ADDRESS_POSTAL_CODE"));
            bookLibraryDTO.setIdLibrary(rs.getString("ID_LIBRARY"));
            return bookLibraryDTO;
        }
    }



}
