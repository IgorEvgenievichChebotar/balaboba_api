package balaboba;

import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class Balaboba {
    public static String continueText(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://yandex.ru/lab/api/yalm/text3";
        return Objects.requireNonNull(
                        restTemplate.postForObject(
                                url,
                                new Request(query),
                                Response.class))
                .getText();
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
