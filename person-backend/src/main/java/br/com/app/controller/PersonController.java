package br.com.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.data.vo.v1.PersonVO;
import br.com.app.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// @CrossOrigin
@Api(value = "Person EndPoint", description = "Description for Person", tags = { "person" })
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    @ApiOperation(value = "Find all people recorded")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page, //
                                     @RequestParam(value = "size", defaultValue = "100") int size, //
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "asc".equalsIgnoreCase(direction) ? Direction.ASC : Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> vos = service.findAll(pageable);

        vos.stream().forEach(vo -> vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel()));

        PagedModel<EntityModel<PersonVO>> model = assembler.toModel(vos);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @ApiOperation(value = "Find all people with token name")
    @GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findPersonByName(@PathVariable("firstName") String firstName, //
                                              @RequestParam(value = "page", defaultValue = "0") int page, //
                                              @RequestParam(value = "size", defaultValue = "100") int size, //
                                              @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "asc".equalsIgnoreCase(direction) ? Direction.ASC : Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> vos = service.findPersonByName(firstName, pageable);

        vos.stream().forEach(vo -> vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel()));

        PagedModel<EntityModel<PersonVO>> model = assembler.toModel(vos);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    // @CrossOrigin(origins= "http://localhost:8084")
    @ApiOperation(value = "Find a specific person by your ID")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO findById(@PathVariable("id") Long id) {
        PersonVO vo = service.findById(id);

        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel()); // Pega o path do PersonController (/api.com/person/v1), adiciona um auto relacionamento, ou seja, um link para ele mesmo no método findById

        return vo;
    }

    // @CrossOrigin(origins= {"http://localhost:8084", "http://www.teste.com.br"})
    @ApiOperation(value = "Create a new person")
    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO vo = service.create(person);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    @ApiOperation(value = "Update a specific person")
    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO vo = service.update(person);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    @ApiOperation(value = "Delete a specific person by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Disable a specific person by your ID")
    @PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO disablePerson(@PathVariable("id") Long id) {
        PersonVO vo = service.disablePerson(id);

        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

}