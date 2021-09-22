package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.repository.CategoryRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();

        Partner partner = Partner.builder()
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .partnerNumber(partnerApiRequest.getPartnerNumber())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .registeredAt(partnerApiRequest.getRegisteredAt())
                .unregisteredAt(partnerApiRequest.getUnregisteredAt())
                .category(categoryRepository.getById(partnerApiRequest.getCategoryId()))
                .build();
        Partner newPartner = partnerRepository.save(partner);

        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Long id, Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();

        return partnerRepository.findById(id)
                .map(partner -> {
                    partner
                            .setName(partnerApiRequest.getName())
                            .setStatus(partnerApiRequest.getStatus())
                            .setAddress(partnerApiRequest.getAddress())
                            .setCallCenter(partnerApiRequest.getCallCenter())
                            .setPartnerNumber(partnerApiRequest.getPartnerNumber())
                            .setBusinessNumber(partnerApiRequest.getBusinessNumber())
                            .setCeoName(partnerApiRequest.getCeoName())
                            .setRegisteredAt(partnerApiRequest.getRegisteredAt())
                            .setUnregisteredAt(partnerApiRequest.getUnregisteredAt())
                            .setCategory(categoryRepository.getById(partnerApiRequest.getCategoryId()))
                            ;
                    return partnerRepository.save(partner);
                })
                .map(partner -> response(partner))
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> partner = partnerRepository.findById(id);

        return partner
                .map(targetPartner -> {
                    partnerRepository.delete(targetPartner);
                    return Header.ok();
                })
                .orElseGet(() -> Header.error("데이터 없음"));
    }


    private Header<PartnerApiResponse> response(Partner partner) {
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return Header.ok(partnerApiResponse);
    }

}
