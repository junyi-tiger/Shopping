package com.shopping.service;

import com.shopping.entity.Card;
import com.shopping.entity.Page;
import com.shopping.utils.Response;

import java.util.List;

public interface CardService {
   public List<Card> getAllCards();
   public List<Card> getCardsByUser(int userId);
    public void addCard(Card card);
    public List<Card> pageQuery(int start,int num);
    public Response deleteCard(int id);
}
