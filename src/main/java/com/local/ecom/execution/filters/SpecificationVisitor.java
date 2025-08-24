package com.local.ecom.execution.filters;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationVisitor<T> implements RSQLVisitor<Specification<T>, Void> {
    private final SpecificationBuilder<T> builder = new SpecificationBuilder<>();

    @Override
    public Specification<T> visit(AndNode node, Void param) {
        return builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(OrNode orNode, Void unused) {
        return builder.createSpecification(orNode);
    }

    @Override
    public Specification<T> visit(ComparisonNode comparisonNode, Void unused) {
        return builder.createSpecification(comparisonNode);
    }
}
