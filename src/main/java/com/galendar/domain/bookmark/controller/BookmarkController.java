package com.galendar.domain.bookmark.controller;

import com.galendar.domain.bookmark.dto.res.BookmarkResponse;
import com.galendar.domain.bookmark.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
@Tag(name = "bookmark", description = "대회 북마크 관련 API")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping
    @Operation(
            summary = "특정 대회를 북마크합니다.",
            description = "북마크하고자 하는 대회의 아이디를 파라미터로 전달합니다."
    )
    public ResponseEntity<?> addBookmark(@RequestParam(name = "contest-id") Long contestId) throws Exception {
        bookmarkService.addBookmark(contestId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(
            summary = "특정 대회의 북마크를 삭제합니다.",
            description = "북마크 삭제하고자 하는 게시물의 아이디를 파라미터로 전달합니다."
    )
    public ResponseEntity<?> removeBookmark(@RequestParam(name = "contest-id") Long contestId) throws Exception {
        bookmarkService.removeBookmark(contestId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(
            summary = "북마크 목록을 불러옵니다.",
            description = "인자는 없습니다."
    )
    public ResponseEntity<List<BookmarkResponse>> getBookmarks() {
        return ResponseEntity.ok(bookmarkService.getBookmarkedContestsByUser());
    }
}
