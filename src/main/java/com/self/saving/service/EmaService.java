package com.self.saving.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.self.saving.repository.EmaRepository;

@Service
@AllArgsConstructor
public class EmaService {

    private final EmaRepository EmaRepository;


}
