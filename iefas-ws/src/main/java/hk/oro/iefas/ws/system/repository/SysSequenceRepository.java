package hk.oro.iefas.ws.system.repository;

import hk.oro.iefas.domain.system.entity.SysSequence;
import hk.oro.iefas.ws.core.repository.BaseRepository;

public interface SysSequenceRepository extends BaseRepository<SysSequence, Integer> {
    SysSequence findBySeqCode(String seq_code);
}
