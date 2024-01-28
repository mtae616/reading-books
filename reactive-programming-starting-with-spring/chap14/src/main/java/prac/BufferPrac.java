package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class BufferPrac {
    public static void main(String[] args) {
        Flux.range(1, 95)
                .buffer(10)
                .subscribe(buffer -> log.info("buffer: {}", buffer));

//        15:09:27.189 [main] INFO prac.BufferPrac -- buffer: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [31, 32, 33, 34, 35, 36, 37, 38, 39, 40]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [41, 42, 43, 44, 45, 46, 47, 48, 49, 50]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [51, 52, 53, 54, 55, 56, 57, 58, 59, 60]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [61, 62, 63, 64, 65, 66, 67, 68, 69, 70]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [71, 72, 73, 74, 75, 76, 77, 78, 79, 80]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [81, 82, 83, 84, 85, 86, 87, 88, 89, 90]
//        15:09:27.191 [main] INFO prac.BufferPrac -- buffer: [91, 92, 93, 94, 95]
    }
}
