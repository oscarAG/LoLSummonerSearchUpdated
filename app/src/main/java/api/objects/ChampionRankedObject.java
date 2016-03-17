package api.objects;

import android.util.Log;

/**
 * Created by Oscar on 3/17/2016.
 */
public class ChampionRankedObject {
    int id;
    int totalDeathsPerSession;
    int totalSessionsPlayed;
    int totalDamageTaken;
    int totalQuadraKills;
    int totalTripleKills;
    int totalMinionKills;
    int maxChampionsKilled;
    int totalDoubleKills;
    int totalPhysicalDamageDealt;
    int totalChampionKills;
    int totalAssists;
    int mostChampionKillsPerSession;
    int totalDamageDealt;
    int totalFirstBlood;
    int totalSessionsLost;
    int totalSessionsWon;
    int totalMagicDamageDealt;
    int totalGoldEarned;
    int totalPentaKills;
    int totalTurretsKilled;
    int mostSpellsCast;
    int maxNumDeaths;
    int totalUnrealKills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        //todo: using this id, translate it into static data about the champion
        this.id = id;
    }

    public int getTotalDeathsPerSession() {
        return totalDeathsPerSession;
    }

    public void setTotalDeathsPerSession(int totalDeathsPerSession) {
        this.totalDeathsPerSession = totalDeathsPerSession;
    }

    public int getTotalSessionsPlayed() {
        return totalSessionsPlayed;
    }

    public void setTotalSessionsPlayed(int totalSessionsPlayed) {
        this.totalSessionsPlayed = totalSessionsPlayed;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(int totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public int getTotalQuadraKills() {
        return totalQuadraKills;
    }

    public void setTotalQuadraKills(int totalQuadraKills) {
        this.totalQuadraKills = totalQuadraKills;
    }

    public int getTotalTripleKills() {
        return totalTripleKills;
    }

    public void setTotalTripleKills(int totalTripleKills) {
        this.totalTripleKills = totalTripleKills;
    }

    public int getTotalMinionKills() {
        return totalMinionKills;
    }

    public void setTotalMinionKills(int totalMinionKills) {
        this.totalMinionKills = totalMinionKills;
    }

    public int getMaxChampionsKilled() {
        return maxChampionsKilled;
    }

    public void setMaxChampionsKilled(int maxChampionsKilled) {
        this.maxChampionsKilled = maxChampionsKilled;
    }

    public int getTotalDoubleKills() {
        return totalDoubleKills;
    }

    public void setTotalDoubleKills(int totalDoubleKills) {
        this.totalDoubleKills = totalDoubleKills;
    }

    public int getTotalPhysicalDamageDealt() {
        return totalPhysicalDamageDealt;
    }

    public void setTotalPhysicalDamageDealt(int totalPhysicalDamageDealt) {
        this.totalPhysicalDamageDealt = totalPhysicalDamageDealt;
    }

    public int getTotalChampionKills() {
        return totalChampionKills;
    }

    public void setTotalChampionKills(int totalChampionKills) {
        this.totalChampionKills = totalChampionKills;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public void setTotalAssists(int totalAssists) {
        this.totalAssists = totalAssists;
    }

    public int getMostChampionKillsPerSession() {
        return mostChampionKillsPerSession;
    }

    public void setMostChampionKillsPerSession(int mostChampionKillsPerSession) {
        this.mostChampionKillsPerSession = mostChampionKillsPerSession;
    }

    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public void setTotalDamageDealt(int totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    public int getTotalFirstBlood() {
        return totalFirstBlood;
    }

    public void setTotalFirstBlood(int totalFirstBlood) {
        this.totalFirstBlood = totalFirstBlood;
    }

    public int getTotalSessionsLost() {
        return totalSessionsLost;
    }

    public void setTotalSessionsLost(int totalSessionsLost) {
        this.totalSessionsLost = totalSessionsLost;
    }

    public int getTotalSessionsWon() {
        return totalSessionsWon;
    }

    public void setTotalSessionsWon(int totalSessionsWon) {
        this.totalSessionsWon = totalSessionsWon;
    }

    public int getTotalMagicDamageDealt() {
        return totalMagicDamageDealt;
    }

    public void setTotalMagicDamageDealt(int totalMagicDamageDealt) {
        this.totalMagicDamageDealt = totalMagicDamageDealt;
    }

    public int getTotalGoldEarned() {
        return totalGoldEarned;
    }

    public void setTotalGoldEarned(int totalGoldEarned) {
        this.totalGoldEarned = totalGoldEarned;
    }

    public int getTotalPentaKills() {
        return totalPentaKills;
    }

    public void setTotalPentaKills(int totalPentaKills) {
        this.totalPentaKills = totalPentaKills;
    }

    public int getTotalTurretsKilled() {
        return totalTurretsKilled;
    }

    public void setTotalTurretsKilled(int totalTurretsKilled) {
        this.totalTurretsKilled = totalTurretsKilled;
    }

    public int getMostSpellsCast() {
        return mostSpellsCast;
    }

    public void setMostSpellsCast(int mostSpellsCast) {
        this.mostSpellsCast = mostSpellsCast;
    }

    public int getMaxNumDeaths() {
        return maxNumDeaths;
    }

    public void setMaxNumDeaths(int maxNumDeaths) {
        this.maxNumDeaths = maxNumDeaths;
    }

    public int getTotalUnrealKills() {
        return totalUnrealKills;
    }

    public void setTotalUnrealKills(int totalUnrealKills) {
        this.totalUnrealKills = totalUnrealKills;
    }

    public void printInfo(){
        Log.d("myapp", "ID: " + getId() + "\n" +
                        "Total Deaths per Session: " + getTotalDeathsPerSession() + "\n" +
                        "Total Sessions played: " + getTotalSessionsPlayed() + "\n" +
                        "Total Damage Taken: " + getTotalDamageTaken() + "\n" +
                        "Total Damage Dealt: " + getTotalDamageDealt() + "\n" +
                        "Total Penta Kills: " + getTotalPentaKills() + "\n" +
                        "Total Quadra Kills: " + getTotalQuadraKills() + "\n" +
                        "Total Triple Kills: " + getTotalTripleKills() + "\n" +
                        "Total Double Kills: " + getTotalDoubleKills() + "\n" +
                        "Total Minion Kills: " + getTotalMinionKills() + "\n" +
                        "Total Magic Damage Dealt: " + getTotalMagicDamageDealt() + "\n" +
                        "Total Physical Damage Dealt: " + getTotalPhysicalDamageDealt() + "\n" +
                        "Max Champion Kills: " + getMaxChampionsKilled() + "\n" +
                        "Total Champion Kills: " + getTotalChampionKills() + "\n" +
                        "Total Assists: " + getTotalAssists() + "\n" +
                        "Most Champion Kills Per Session: " + getMostChampionKillsPerSession() + "\n" +
                        "Total First Blood: " + getTotalFirstBlood() + "\n" +
                        "Total Sessions Played: " + getTotalSessionsPlayed() + "\n" +
                        "Total Sessions Won: " + getTotalSessionsWon() + "\n" +
                        "Total Sessions Lost: " + getTotalSessionsLost() + "\n" +
                        "Total Gold Earned: "  + getTotalGoldEarned() + "\n" +
                        "Total Turrets Killed: " + getTotalTurretsKilled() + "\n" +
                        "Most Spells Cast: " + getMostSpellsCast() + "\n" +
                        "Max Num Deaths: " + getMaxNumDeaths() + "\n" +
                        "Total Unreal Kills: " + getTotalUnrealKills());
    }
}
