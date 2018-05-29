package tmelo.chucknorrisjokes.services;

import org.springframework.stereotype.Service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;

@Service
public class JokeServiceImpl implements JokeService {

	private final ChuckNorrisQuotes quotes;

	public JokeServiceImpl(ChuckNorrisQuotes chuck) {
		this.quotes = chuck;
	}

	@Override
	public String getRandomJoke() {
		return quotes.getRandomQuote();
	}

}
