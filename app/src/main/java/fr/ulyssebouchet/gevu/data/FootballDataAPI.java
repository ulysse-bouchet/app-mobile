package fr.ulyssebouchet.gevu.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FootballDataAPI {
    private static final String API_TOKEN_1 = "78ebbde6f7f042ccb1b06e1757e5735b";
    private static final String API_TOKEN_2 = "1f217007d7cf4d73a08c8c5e878b0509";
    private static final String API_TOKEN_3 = "ed69af25a8284535a5aa750f2ee6fb33";
    private static final String API_TOKEN_4 = "6e58bc6cabaa4f7aac268814f7420d09";
    private static final String API_TOKEN_5 = "51ef7ab393234cd59095ffdb053ac98b";
    private static final String API_TOKEN_6 = "6350dea7450343ca8a9e25dbcfa1e187";

    public static final int ID_LIGUE1 = 2015;
    public static final int ID_BUNDESLIGA = 2002;
    public static final int ID_LIGA = 2014;
    public static final int ID_PL = 2021;
    public static final int ID_SERIE_A = 2019;

    private static final OkHttpClient client = new OkHttpClient();

    private static int cptToken = 0;
    private static String getToken() {
        switch (cptToken++ % 6) {
            case 0: return API_TOKEN_1;
            case 1: return API_TOKEN_2;
            case 2: return API_TOKEN_3;
            case 3: return API_TOKEN_4;
            case 4: return API_TOKEN_5;
            case 5: return API_TOKEN_6;
            default: return API_TOKEN_1;
        }
    }

    public static int getMatchDay(int leagueID) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.football-data.org/v2/competitions/" + leagueID)
                .header("X-Auth-Token", getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new Exception();

            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getJSONObject("currentSeason").getInt("currentMatchday");
        }
    }

    public static List<Match> getMatches(int leagueID, int matchDay) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.football-data.org/v2/competitions/" + leagueID
                        + "/matches?matchday=" + matchDay)
                .header("X-Auth-Token", getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new Exception();

            JSONObject jsonResponse = new JSONObject(response.body().string());
            JSONArray jsonMatches = jsonResponse.getJSONArray("matches");

            List<Match> matches = new ArrayList<>();
            for (int i = 0; i < jsonMatches.length(); ++i) {
                JSONObject jsonMatch = jsonMatches.getJSONObject(i);

                String status = jsonMatch.getString("status");

                if(status.equals("POSTPONED") || status.equals("SCHEDULED"))
                    continue;

                int id = jsonMatch.getJSONObject("homeTeam").getInt("id");
                String homeTeam = jsonMatch.getJSONObject("homeTeam").getString("name");
                String awayTeam = jsonMatch.getJSONObject("awayTeam").getString("name");

                JSONObject scoreFullTime = jsonMatch.getJSONObject("score").getJSONObject("fullTime");
                int homeTeamGoals = scoreFullTime.getInt("homeTeam");
                int awayTeamGoals = scoreFullTime.getInt("awayTeam");

                String date = jsonMatch.getString("utcDate");

                matches.add(new Match(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, date));
            }
            return matches;
        }
    }

    public static List<Match> getMatches(int leagueID) throws Exception {
        int matchDay = getMatchDay(leagueID);
        Request request = new Request.Builder()
                .url("https://api.football-data.org/v2/competitions/" + leagueID
                        + "/matches?matchday=" + matchDay)
                .header("X-Auth-Token", getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new Exception();

            JSONObject jsonResponse = new JSONObject(response.body().string());
            JSONArray jsonMatches = jsonResponse.getJSONArray("matches");

            List<Match> matches = new ArrayList<>();
            boolean atleastOneMatch = false;
            for (int i = 0; i < jsonMatches.length(); ++i) {
                JSONObject jsonMatch = jsonMatches.getJSONObject(i);

                String status = jsonMatch.getString("status");

                if(status.equals("POSTPONED") || status.equals("SCHEDULED"))
                    continue;

                int id = jsonMatch.getJSONObject("homeTeam").getInt("id");
                String homeTeam = jsonMatch.getJSONObject("homeTeam").getString("name");
                String awayTeam = jsonMatch.getJSONObject("awayTeam").getString("name");

                JSONObject scoreFullTime = jsonMatch.getJSONObject("score").getJSONObject("fullTime");
                int homeTeamGoals = scoreFullTime.getInt("homeTeam");
                int awayTeamGoals = scoreFullTime.getInt("awayTeam");

                String date = jsonMatch.getString("utcDate");

                matches.add(new Match(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, date));
                atleastOneMatch = true;
            }
            if (!atleastOneMatch)
                return matchDay - 1 < 1 ? null : getMatches(leagueID, matchDay - 1);
            return matches;
        }
    }
}
