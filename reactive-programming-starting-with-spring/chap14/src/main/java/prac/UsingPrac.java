package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class UsingPrac {
    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("/static/test.txt");
//                         한 라인씩 읽어옴,      스트림으로 변환,      스트림 닫기
//        Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close)
//                .subscribe(log::info);

        String content;
        InputStream inputStream = UsingPrac.class.getResourceAsStream("/static/test.txt");
        if (inputStream != null) {
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            Flux.using(() -> content, Flux::just, c -> {})
                    .subscribe(log::info);
        }

    }
}
