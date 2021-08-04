package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderDetailRepository;
import com.example.study.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(body.getArrivalDate())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getById(body.getOrderGroupId()))
                .item(itemRepository.getById(body.getItemId()))
                .build();

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> response(orderDetail))
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Long id, Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    orderDetail
                            .setStatus(body.getStatus())
                            .setArrivalDate(body.getArrivalDate())
                            .setQuantity(body.getQuantity())
                            .setTotalPrice(body.getTotalPrice())
                            .setOrderGroup(orderGroupRepository.getById(body.getOrderGroupId()))
                            .setItem(itemRepository.getById(body.getItemId()))
                            ;
                    return response(orderDetailRepository.save(orderDetail));
                })
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    orderDetailRepository.delete(orderDetail);
                    return Header.ok();
                })
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
        return Header.ok(OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build()
        );
    }

}
