package app.web.services;

import app.web.data.MatchResultRepository;
import app.web.domain.MatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MatchResultServiceImpl implements MatchResultService{

    @Autowired
    MatchResultRepository matchResultRepository;

    public MatchResult save(MatchResult matchResult){
        return matchResultRepository.save(matchResult);
    }
}
