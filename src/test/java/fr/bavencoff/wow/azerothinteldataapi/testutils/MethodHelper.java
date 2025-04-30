package fr.bavencoff.wow.azerothinteldataapi.testutils;

import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MethodHelper {

    public static final ResultMatcher[] RFC_7807_MATCHERS = {
            jsonPath("$.type").isNotEmpty(),
            jsonPath("$.title").isNotEmpty(),
            jsonPath("$.status").isNotEmpty(),
            jsonPath("$.detail").isNotEmpty(),
            jsonPath("$.instance").isNotEmpty()
    };

    public static String jsonToString(
            String path
    ) throws IOException {
        return Files.readString(Path.of("src/test/resources/" + path));
    }
}
