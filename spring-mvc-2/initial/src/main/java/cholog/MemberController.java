package cholog;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    private final List<Member> members = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(1);

    @PostMapping("/members")
    public ResponseEntity<Void> create(@RequestBody final Member member) {
        Member newMember = Member.toEntity(member, index.getAndIncrement());
        members.add(newMember);
        return ResponseEntity.created(URI.create("/members/" + newMember.getId())).build();
    }
        // TODO: member 정보를 받아서 생성한다.

    @GetMapping("/members")
    public ResponseEntity<List<Member>> read() {
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
        // TODO: 저장된 모든 member 정보를 반환한다.

    @PutMapping("/members/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @RequestBody final Member newMember) {
        // TODO: member의 수정 정보와 url 상의 id 정보를 받아 member 정보를 수정한다.
        Member member = members.stream()
            .filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElseThrow(RuntimeException::new);

        member.update(newMember);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        // TODO: url 상의 id 정보를 받아 member를 삭제한다.
        Member member = members.stream()
            .filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElseThrow(RuntimeException::new);

        members.remove(member);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
