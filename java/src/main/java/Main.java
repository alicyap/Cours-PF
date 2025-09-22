import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static int score(String word) {
        // score = nombre de lettres - nombre de 'a'
        return word.replaceAll("a", "").length();
    }

    static int scoreWithBonus(String word) {
        // score = nombre de lettres - nombre de 'a' + bonus si le mot contient un 'c'
        int bonus = word.contains("c") ? 5 : 0;
        return score(word) + bonus;
    }

    static Comparator<String> genComparator(Function<String, Integer> scoring) {
        return (o1, o2) -> Integer.compare(scoring.apply(o2), scoring.apply(o1));
    }

    //lambda fonction
    static Comparator<String> scoreCompateur =
            ((o1, o2) -> Integer.compare(score(o2), score(o1)));

    static List<String> rankedWords(Comparator<String> comp, List<String> words) {
        List<String> rankedWords = new ArrayList<>(words);
        rankedWords.sort(comp);
        return rankedWords;
    }

    static Comparator<String> scoreWithBonusCompateur =
            ((o1, o2) -> Integer.compare(scoreWithBonus(o2), scoreWithBonus(o1)));

    public interface CalculScore extends Function<String, Integer> {

    }

    static CalculScore score = word -> word.replaceAll("a", "").length();
    static CalculScore bonus = word -> word.contains("c") ? 5 : 0;
    static CalculScore penalty = word -> word.contains("s") ? 7 : 0;

    static CalculScore scoreTotal(CalculScore score, CalculScore bonus, CalculScore malus) {
        return word -> score.apply(word) + bonus.apply(word) - malus.apply(word);
    }


    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherries", "coconut", "carrots");
        System.out.println("Liste à l'origine : " + words);
        //words.sort(new org.example.ComparateurMot()); --> pas fonctionel, effet de bord

        List<String> rankedWords = new ArrayList<>(words);
        rankedWords.sort(scoreCompateur);
        System.out.println("Liste triée dans l'ordre décroissant : " + rankedWords);

        List<String> rankedWordsWithBonus = new ArrayList<>(words);
        rankedWordsWithBonus.sort(scoreWithBonusCompateur);
        System.out.println("Liste triée avec bonus dans l'ordre décroissant : " + rankedWordsWithBonus);

        System.out.println("Liste de mots triée avec bonus et malus :");
        System.out.println(rankedWords(genComparator(scoreTotal(score, bonus, penalty)), words));


    }
}
