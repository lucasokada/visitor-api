package com.example.visitor_ms.domain.usecase;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;

public interface CreateVisitorUseCase {
    Visitor create(CreateVisitorCommand personCommand);
}
