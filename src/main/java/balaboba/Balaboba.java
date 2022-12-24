package balaboba;

import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Objects;

public class Balaboba {
    private File file;

    public Balaboba(String pathToFile) {
        this.file = new File(pathToFile);
    }

    public Balaboba() {
    }

    public String continueText(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://yandex.ru/lab/api/yalm/text3";
        return Objects.requireNonNull(
                        restTemplate.postForObject(
                                url,
                                new Request(query),
                                Response.class))
                .getText();
    }

    public void writeToFileContinuedText() throws IOException {
        String query;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String s = reader.readLine();
                if (s == null) break;
                sb.append(s);
            }
            sb.append(" ");
            query = sb.toString();
        }
        String continuedText = this.continueText(query);
        query += continuedText;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            writer.write(query);
        }
    }

    private static class Request {
        private String query;

        public Request() {
        }

        public Request(String query) {
            this.query = query;
            int intro = 0;
            int filter = 1;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }
    }

    private static class Response {
        private String text;

        public String getText() {
            return text;
        }
    }
}
