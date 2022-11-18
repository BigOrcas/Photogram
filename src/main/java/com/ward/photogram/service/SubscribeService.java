package com.ward.photogram.service;


import com.ward.photogram.domain.subscribe.Subscribe;
import com.ward.photogram.domain.subscribe.SubscribeRepository;
import com.ward.photogram.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserId,int toUserId){
        try{
            subscribeRepository.mSubscribe(fromUserId,toUserId);
        }catch (Exception e){
            throw new CustomValidationApiException("이미 구독을 하였습니다.");
        }

    }
    @Transactional
    public void 구독취소하기(int fromUserId,int toUserId){
    subscribeRepository.mUnSubscribe(fromUserId,toUserId);

    }


}
