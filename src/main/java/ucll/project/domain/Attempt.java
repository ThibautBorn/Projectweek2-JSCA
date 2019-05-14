package ucll.project.domain;

import ucll.project.db.DatabaseService;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Attempt {
    Date date;
    Map<Tent, Map<Question, Answer>> previousChoices = new HashMap<>();


    public Attempt(Date date) {
        setDate(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addChoicesPerTent(Tent tent, Map<Question, Answer> chosenChoices) {
        previousChoices.put(tent, chosenChoices);
    }

    public Map<Tent, Double> getScorePerTent() {
        Map<Tent, Double> result = new HashMap<>();
        for (Map.Entry<Tent, Map<Question, Answer>> tentEntry : previousChoices.entrySet()) {
            Tent competent = tentEntry.getKey();
            result.put(competent, getScoreOfTent(competent));
        }
        return result;
    }

    public double getScoreOfTent(Tent tent) {
        double totalScoreTent = 0;
        Map<Question, Answer> competent = previousChoices.get(tent);
        if (competent!=null) {
            for (Question q : competent.keySet()) {
                totalScoreTent += competent.get(q).getPoint();
            }
        }
        return totalScoreTent;
    }

    public double getTotalScore() {
        double totaal = 0;
        for (Map.Entry<Tent, Double> entry : this.getScorePerTent().entrySet()) {
            totaal += entry.getValue();
        }
        return totaal ;
    }

    public double getPercentage() {
        return getTotalScore()/getTotalQuestionsAnswered() * 100;
    }

    public int getTotalQuestionsAnswered() {
        int total = 0;

        for (Map.Entry<Tent, Map<Question, Answer>> tentEntry : previousChoices.entrySet()) {
            total += tentEntry.getValue().size();
        }
        return total;
    }

    public Map<Tent, Double> getTotalPercentagesPerTent(DatabaseService service, Grade grade) {
        Map<Tent,Double> aantalPerTent = new HashMap<>();
        for (Tent tent: service.getAllTenten()) {
            double result = getScoreOfTent(tent) / service.getPossibleAmountOfPoints(tent.getId(), grade) * 100;
            aantalPerTent.put(tent, result);
        }
        return aantalPerTent;
    }
}