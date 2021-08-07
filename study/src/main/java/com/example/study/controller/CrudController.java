package com.example.study.controller;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {

//    protected CrudInterface<Req, Res> baseService;
    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("/{id}")
    public Header<Res> update(@PathVariable Long id,
                              @RequestBody Header<Req> request) {
        return baseService.update(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }

}
