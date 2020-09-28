package br.com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.converter.DozerConverter;
import br.com.app.data.model.BookEntity;
import br.com.app.data.vo.v1.BookVO;
import br.com.app.exception.ResourceNotFoundException;
import br.com.app.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookVO create(BookVO book) {
        var entity = DozerConverter.parseObject(book, BookEntity.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public List<BookVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
    }

    public BookVO findById(Long id) {
        var entity = repository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public BookVO update(BookVO book) {
        var entity = repository.findById(book.getKey()) //
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        BookEntity entity = repository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
