package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

public class Class89
implements Closeable {
    private final String CLIENT_ID = "67b74668-ef33-49c3-a75c-18cbb2481e0c";
    private final String REDIRECT_URI = "http://localhost:3434/sad";
    private final String SCOPE = "XboxLive.signin%20offline_access";
    private final Gson gson = new GsonBuilder().create();
    private final JsonParser parser = new JsonParser();
    private final HttpServer httpServer;
    private boolean logged = false;
    private String uuid;
    private String userName;
    private String accessToken;

    public Class89(Class91 manager) {
        try {
            this.httpServer = HttpServer.create(new InetSocketAddress("localhost", 3434), 0);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.httpServer.createContext("/sad", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            if (query.contains("code")) {
                String code = query.split("code=")[1];
                String[] microsoftTokenAndRefreshToken = this.getMicrosoftTokenAndRefreshToken(code);
                String xBoxLiveToken = this.getXBoxLiveToken(microsoftTokenAndRefreshToken[0]);
                String[] xstsTokenAndHash = this.getXSTSTokenAndUserHash(xBoxLiveToken);
                String accessToken = this.getAccessToken(xstsTokenAndHash[0], xstsTokenAndHash[1]);
                JsonObject jsonObject = this.parser.parse(this.get("https://api.minecraftservices.com/minecraft/profile", Map.of("Authorization", "Bearer " + accessToken))).getAsJsonObject();
                this.uuid = jsonObject.get("id").getAsString();
                this.userName = jsonObject.get("name").getAsString();
                this.accessToken = accessToken;
                this.logged = true;
            }
        });
        this.httpServer.start();
        try {
            String URL2 = "https://login.live.com/oauth20_authorize.srf?client_id=<client_id>&redirect_uri=<redirect_uri>&response_type=code&display=touch&scope=<scope>".replace("<client_id>", "67b74668-ef33-49c3-a75c-18cbb2481e0c").replace("<redirect_uri>", "http://localhost:3434/sad").replace("<scope>", "XboxLive.signin%20offline_access");
            Desktop.getDesktop().browse(new URI(URL2));
        }
        catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String[] getMicrosoftTokenAndRefreshToken(String code) {
        JsonObject jsonObject;
        try {
            jsonObject = this.parser.parse(this.post("https://login.live.com/oauth20_token.srf", "client_id=67b74668-ef33-49c3-a75c-18cbb2481e0c&code=" + code + "&grant_type=authorization_code&redirect_uri=http://localhost:3434/sad&scope=XboxLive.signin%20offline_access", Map.of("Content-Type", "application/x-www-form-urlencoded"))).getAsJsonObject();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String[]{jsonObject.get("access_token").getAsString(), jsonObject.get("refresh_token").getAsString()};
    }

    private String getXBoxLiveToken(String microsoftToken) {
        JsonObject jsonObject;
        JsonObject paramObj = new JsonObject();
        JsonObject propertiesObj = new JsonObject();
        propertiesObj.addProperty("AuthMethod", "RPS");
        propertiesObj.addProperty("SiteName", "user.auth.xboxlive.com");
        propertiesObj.addProperty("RpsTicket", "d=" + microsoftToken);
        paramObj.add("Properties", propertiesObj);
        paramObj.addProperty("RelyingParty", "http://auth.xboxlive.com");
        paramObj.addProperty("TokenType", "JWT");
        try {
            jsonObject = this.parser.parse(this.post("https://user.auth.xboxlive.com/user/authenticate", this.gson.toJson(paramObj), Map.of("Content-Type", "application/json", "Accept", "application/json"))).getAsJsonObject();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.get("Token").getAsString();
    }

    private String[] getXSTSTokenAndUserHash(String xboxLiveToken) {
        JsonObject jsonObject;
        JsonObject paramObj = new JsonObject();
        JsonObject propertiesObj = new JsonObject();
        propertiesObj.addProperty("SandboxId", "RETAIL");
        propertiesObj.add("UserTokens", this.parser.parse(this.gson.toJson(Collections.singletonList(xboxLiveToken))));
        paramObj.add("Properties", propertiesObj);
        paramObj.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
        paramObj.addProperty("TokenType", "JWT");
        try {
            jsonObject = this.parser.parse(this.post("https://xsts.auth.xboxlive.com/xsts/authorize", this.gson.toJson(paramObj), Map.of("Content-Type", "application/json"))).getAsJsonObject();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String[]{jsonObject.get("Token").getAsString(), jsonObject.get("DisplayClaims").getAsJsonObject().get("xui").getAsJsonArray().get(0).getAsJsonObject().get("uhs").getAsString()};
    }

    private String getAccessToken(String xstsToken, String uhs) {
        JsonObject jsonObject;
        JsonObject paramObj = new JsonObject();
        paramObj.addProperty("identityToken", "XBL3.0 x=" + uhs + ";" + xstsToken);
        try {
            jsonObject = this.parser.parse(this.post("https://api.minecraftservices.com/authentication/login_with_xbox", this.gson.toJson(paramObj), Map.of("Content-Type", "application/json", "Accept", "application/json"))).getAsJsonObject();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.get("access_token").getAsString();
    }

    private String post(String urlString, String param, Map<String, String> requestProperty) throws IOException {
        int len;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setConnectTimeout(20000);
        connection.setReadTimeout(20000);
        connection.setRequestMethod("POST");
        for (Map.Entry<String, String> entry : requestProperty.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(param.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        StringBuilder readText = new StringBuilder();
        InputStream inputStream = connection.getInputStream();
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1) {
            readText.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
        }
        inputStream.close();
        connection.disconnect();
        return readText.toString();
    }

    private String get(String urlString, Map<String, String> requestProperty) throws IOException {
        int len;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(20000);
        connection.setReadTimeout(20000);
        for (Map.Entry<String, String> entry : requestProperty.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        StringBuilder readText = new StringBuilder();
        InputStream inputStream = connection.getInputStream();
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1) {
            readText.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
        }
        inputStream.close();
        connection.disconnect();
        return readText.toString();
    }

    @Override
    public void close() {
        if (this.httpServer != null) {
            this.httpServer.stop(0);
        }
    }

    public String getCLIENT_ID() {
        return this.CLIENT_ID;
    }

    public String getREDIRECT_URI() {
        return this.REDIRECT_URI;
    }

    public String getSCOPE() {
        return this.SCOPE;
    }

    public Gson getGson() {
        return this.gson;
    }

    public JsonParser getParser() {
        return this.parser;
    }

    public HttpServer getHttpServer() {
        return this.httpServer;
    }

    public boolean isLogged() {
        return this.logged;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

