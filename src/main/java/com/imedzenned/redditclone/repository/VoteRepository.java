package com.imedzenned.redditclone.repository;

import com.imedzenned.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository <Vote, Long> {
}
