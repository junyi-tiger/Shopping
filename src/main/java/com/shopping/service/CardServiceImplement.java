package com.shopping.service;

import com.shopping.entity.Card;
import com.shopping.entity.Page;
import com.shopping.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.dao.CardDao;
import java.util.List;
@Service
public class CardServiceImplement implements CardService {

    @Autowired
    private CardDao cardDao;

    private List<Card> cards;

    @Override
    public List<Card> getAllCards() {
        return cardDao.getAllCards();
    }

    @Override
    public List<Card> getCardsByUser(int userId) {
        return cardDao.getCardsByUser(userId);
    }

    @Override
    public void addCard(Card card) {
        cardDao.addCard(card);
    }

    @Override
    public List<Card> pageQuery(int start, int num) {
        return cardDao.pageQuery(start,num);
    }

    @Override
    public Response deleteCard(int id) {
        try {
            cardDao.deleteCard(id);
            return new Response(1, "删除帖子成功", null);
        }catch (Exception e){
            return new Response(0,"删除帖子失败",null);
        }
    }


}
