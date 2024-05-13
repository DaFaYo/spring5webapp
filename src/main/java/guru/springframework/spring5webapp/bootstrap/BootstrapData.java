package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher prometheus = new Publisher("Prometheus",
                "Herengracht 48",
                "Amsterdam",
                "1015 BN",
                "The Netherlands"
        );

        publisherRepository.save(prometheus);

        Author robert = new Author("Robert", "Harris");
        Book fatherland = new Book("Fatherland", "978-0-09-952789-3");
        robert.getBooks().add(fatherland);
        fatherland.getAuthors().add(robert);
        fatherland.setPublisher(prometheus);
        prometheus.getBooks().add(fatherland);

        authorRepository.save(robert);
        bookRepository.save(fatherland);
        publisherRepository.save(prometheus);

        Author kafka = new Author("Franz", "Kafka");
        Book process = new Book("Het proces", "9-789025-312671");
        kafka.getBooks().add(process);
        process.getAuthors().add(kafka);
        process.setPublisher(prometheus);
        prometheus.getBooks().add(process);

        authorRepository.save(kafka);
        bookRepository.save(process);
        publisherRepository.save(prometheus);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + prometheus.getBooks().size());




    }
}
