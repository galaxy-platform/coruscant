package com.github.willrees23.coruscant.punishment;

import io.github.galaxyplatform.punishment.Punishment;
import io.github.galaxyplatform.punishment.Revocation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/punishments")
public class PunishmentController {

    private final PunishmentService service;

    public PunishmentController(PunishmentService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Punishment> list() {
        return service.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Punishment> get(@PathVariable UUID id) {
        return service.byId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/target/{targetUuid}")
    public List<Punishment> forTarget(@PathVariable UUID targetUuid) {
        return service.forTarget(targetUuid);
    }

    @PostMapping
    public Punishment create(@RequestBody Punishment punishment) {
        return service.save(punishment);
    }

    @PostMapping("/{id}/revoke")
    public ResponseEntity<Punishment> revoke(@PathVariable UUID id, @RequestBody Revocation revocation) {
        return service.revoke(id, revocation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
