package fr.ulyssebouchet.gevu.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FootballDataAPI {
    private static final String API_TOKEN = "78ebbde6f7f042ccb1b06e1757e5735b";
    public static final int ID_LIGUE1 = 2015;

    private static final OkHttpClient client = new OkHttpClient();

    public static int getMatchDay(int leagueID) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.football-data.org/v2/competitions/" + leagueID)
                .header("X-Auth-Token", API_TOKEN)
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
                .header("X-Auth-Token", API_TOKEN)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new Exception();

            JSONObject jsonResponse = new JSONObject(response.body().string());
            JSONArray jsonMatches = jsonResponse.getJSONArray("matches");

            List<Match> matches = new ArrayList<>();
            for (int i = 0; i < jsonMatches.length(); ++i) {
                JSONObject jsonMatch = jsonMatches.getJSONObject(i);
                String homeTeam = jsonMatch.getJSONObject("homeTeam").getString("name");
                String awayTeam = jsonMatch.getJSONObject("awayTeam").getString("name");
                matches.add(new Match(homeTeam, awayTeam));
            }
            return matches;
        }
    }
}
