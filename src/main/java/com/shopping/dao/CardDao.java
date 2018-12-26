package com.shopping.dao;

import com.shopping.entity.Card;

import java.util.List;

public interface CardDao {
   public List<Card> getAllCards();
   public List<Card> getCardsByUser(int userId);
   public void addCard(Card card);
   public List<Card> pageQuery(int start,int num);
   public boolean deleteCard(int id);
}
