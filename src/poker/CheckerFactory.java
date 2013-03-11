package poker;

public class CheckerFactory {
	
	private static final CheckerFactory instance = new CheckerFactory();	//others depending on game type

	public static CheckerFactory getInstance(GameType game) {
		return instance;
	}

	public Checker getChecker() {
		return new CheckerImpl();	//consider other checker options?
	}

}
