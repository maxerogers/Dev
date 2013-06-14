package com.example.littleblind;

public class Card 
{
	private static final String[] paths =
		{ 
		"2_of_clubs.png",
		"3_of_clubs.png",
		"4_of_clubs.png",
		"5_of_clubs.png",
		"6_of_clubs.png",
		"7_of_clubs.png",
		"8_of_clubs.png",
		"9_of_clubs.png",
		"10_of_clubs.png",
		"jack_of_clubs2.png",
		"queen_of_clubs2.png",
		"king_of_clubs2.png",
		"ace_of_clubs2.png",
		"2_of_diamonds.png",
		"3_of_diamonds.png",
		"4_of_diamonds.png",
		"5_of_diamonds.png",
		"6_of_diamonds.png",
		"7_of_diamonds.png",
		"8_of_diamonds.png",
		"9_of_diamonds.png",
		"10_of_diamonds.png",
		"jack_of_diamonds2.png",
		"queen_of_diamonds2.png",
		"king_of_diamonds2.png",
		"ace_of_diamonds2.png",
		"2_of_hearts.png",
		"3_of_hearts.png",
		"4_of_hearts.png",
		"5_of_hearts.png",
		"6_of_hearts.png",
		"7_of_hearts.png",
		"8_of_hearts.png",
		"9_of_hearts.png",
		"10_of_hearts.png",
		"jack_of_hearts2.png",
		"queen_of_hearts2.png",
		"king_of_hearts2.png",
		"ace_of_hearts2.png",
		"2_of_spades.png",
		"3_of_spades.png",
		"4_of_spades.png",
		"5_of_spades.png",
		"6_of_spades.png",
		"7_of_spades.png",
		"8_of_spades.png",
		"9_of_spades.png",
		"10_of_spades.png",
		"jack_of_spades2.png",
		"queen_of_spades2.png",
		"king_of_spades2.png",
		"ace_of_spades2.png",
		"red_joker.png",
		"black_joker.png"
		};
	public int id;
	Card(int x){id = x;}
	public static String getPath(Card c)
	{
		return paths[c.id];
	}
}