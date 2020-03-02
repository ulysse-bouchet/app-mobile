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
    public static final int ID_LIGUE1 = 2015;
    public static final int ID_BUNDESLIGA = 2002;
    public static final int ID_LIGA = 2014;
    public static final int ID_PL = 2021;
    public static final int ID_SERIE_A = 2019;

    private static final OkHttpClient client = new OkHttpClient();

    public static int getMatchDay(int leagueID) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.football-data.org/v2/competitions/" + leagueID)
                .header("X-Auth-Token", API_TOKEN_2)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new RuntimeException();

            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getJSONObject("currentSeason").getInt("currentMatchday");
        }
    }

    public static List<Match> getMatches(int leagueID) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.football-data.org/v2/competitions/" + leagueID
                        + "/matches?matchday=" + getMatchDay(leagueID))
                .header("X-Auth-Token", API_TOKEN_1)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new Exception();

            JSONObject jsonResponse = new JSONObject(response.body().string());
            JSONArray jsonMatches = jsonResponse.getJSONArray("matches");

            List<Match> matches = new ArrayList<>();
            for (int i = 0; i < jsonMatches.length(); ++i) {
                JSONObject jsonMatch = jsonMatches.getJSONObject(i);

                if(jsonMatch.getString("status").equals("POSTPONED"))
                    continue;

                String homeTeam = jsonMatch.getJSONObject("homeTeam").getString("name");
                String awayTeam = jsonMatch.getJSONObject("awayTeam").getString("name");

                JSONObject scoreFullTime = jsonMatch.getJSONObject("score").getJSONObject("fullTime");
                int homeTeamGoals = scoreFullTime.getInt("homeTeam");
                int awayTeamGoals = scoreFullTime.getInt("awayTeam");

                String date = jsonMatch.getString("utcDate");

                matches.add(new Match(homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, date));
            }
            return matches;
        }
    }
}
