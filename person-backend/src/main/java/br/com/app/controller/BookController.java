package br.com.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.data.vo.v1.BookVO;
import br.com.app.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book EndPoint", description = "Description for Book", tags = { "book" })
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private BookService service;

    @ApiOperation(value = "Find all books")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<BookVO> findAll() {
        List<BookVO> vos = service.findAll();

        vos.stream().forEach(vo -> vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel()));

        return vos;
    }

    @ApiOperation(value = "Find a specific book by your ID")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO findById(@PathVariable("id") Long id) {
        BookVO vo = service.findById(id);

        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel()); // Pega o path do BookController (/api.com/book/v1), adiciona um auto relacionamento, ou seja, um link para ele mesmo no método findById

        return vo;
    }

    @ApiOperation(value = "Create a new book")
    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO create(@RequestBody BookVO book) {
        BookVO vo = service.create(book);

        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    @ApiOperation(value = "Update a specific book")
    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO update(@RequestBody BookVO book) {
        BookVO vo = service.update(book);

        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    @ApiOperation(value = "Delete a specific book by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
