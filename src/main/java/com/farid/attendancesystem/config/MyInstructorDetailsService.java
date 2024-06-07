package com.farid.attendancesystem.config;

import com.farid.attendancesystem.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyInstructorDetailsService implements UserDetailsService {

    private final InstructorRepository instructorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         return  instructorRepository.findInstructorByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
