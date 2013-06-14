package com.example.littleblind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Rect;

public class Player 
{
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Rect> handPos = new ArrayList<Rect>();
	
	Player(){}
	public void addCard(Card c)
	{
		hand.add(c);
	}
	public Card popCard(int x)
	{
		Card temp = hand.get(x);
		hand.remove(x);
		return temp;
	}
	public void setRect(Rect[] r)
	{
		for(int i=0;i<r.length;i++)
		{
			handPos.add(r[i]);
		}
	}
	public Map<Card, Rect> getHand()
	{
		HashMap<Card, Rect> result = new HashMap<Card, Rect>();
		for(int i=0;i<hand.size();i++)
		{
			result.put(hand.get(i), handPos.get(i));
		}
		return result;
	}
}
