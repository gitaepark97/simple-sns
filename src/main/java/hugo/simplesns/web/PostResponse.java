package hugo.simplesns.web;

record PostResponse(
    Long id,
    UserResponse writer,
    String title,
    String content,
    Long createTime
) {

    static PostResponse from(PostWithWriter postWithWriter) {
        return new PostResponse(
            postWithWriter.post().getId(),
            UserResponse.from(postWithWriter.writer()),
            postWithWriter.post().getTitle(),
            postWithWriter.post().getContent(),
            postWithWriter.post().getCreateTime()
        );
    }

}
