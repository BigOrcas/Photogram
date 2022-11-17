package com.ward.photogram.service;


import com.ward.photogram.domain.subscribe.Subscribe;
import com.ward.photogram.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserId,int toUserId){
        subscribeRepository.mSubscribe(fromUserId,toUserId);

    }
    @Transactional
    public void 구독취소하기(int fromUserId,int toUserId){
    subscribeRepository.mUnSubscribe(fromUserId,toUserId);

    }


}
