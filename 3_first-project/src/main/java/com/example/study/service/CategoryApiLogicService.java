package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Category;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;
import com.example.study.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                .type(body.getType())
                .title(body.getTitle())
                .build();

        Category newCategory = categoryRepository.save(category);
        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return categoryRepository.findById(id)
                .map(category -> response(category))
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Long id, Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        return categoryRepository.findById(id)
                .map(category -> {
                    category
                            .setType(body.getType())
                            .setTitle(body.getTitle())
                            ;
                    return response(categoryRepository.save(category));
                })
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return Header.ok();
                })
                .orElseGet(() -> Header.error("데이터 없음"));
    }


    private Header<CategoryApiResponse> response(Category category) {
        return Header.ok(CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .build());
    }
}
