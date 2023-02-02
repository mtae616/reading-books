# IO

- stream
  - 데이터를 운반하는데 사용되는 연결통로
  - 단방향통신만 가능, 입력이나 출력 한 가지만 수행
  - 입,출력을 동시에 수행하려면 스트림 2개가 필요하다.
  - FIFO
  - FileInputStream, FileOutputStream
    - 파일
  - ByteArrayInputStream, ByteArrayOutputStream
    - 메모리
  - PipedInputStream, PipedOutputStream
    - IPC
  - AudioInputStream, AudioOutputStream
    - 오디오장치
  - 보조스트림
    - 실제 데이터를 주고받는 기능은 없지만, 스트림의 기능 향상, 새로운 기능 추가
    - BufferedIn/OutputStream
    - FilterIn/OutputStream
    - DataIn/OutputStream
    - SequenceIn/OutputStream
    - LineNumberIn/OutputStream
  - 문자기반스트림
    - 자바는 char가 2byte이기 때문에 위의 바이트 기반 스트림으로 처리 어려움
    - InputStream -> Reader
    - OutputStream -> Writer
    - FileReader/Writer
    - CharArrayReader/Writer
    - PipedReader/Writer
    - StringReader/Writer

## 직렬화

- 객체를 데이터 스트림으로 만드는 것 -> 데이터 단위가 객체
- 