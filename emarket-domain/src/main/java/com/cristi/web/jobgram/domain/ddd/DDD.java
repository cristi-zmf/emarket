package com.cristi.web.jobgram.domain.ddd;

public @interface DDD {
    @interface DomainEntity {}

    @interface ValueObject {}

    @interface ValueObjectId{}

    @interface DomainService{}

    @interface DomainRepository{}

    @interface DomainRepositoryImpl{}

    @interface InfrastructureService{}

    @interface InfrastructureServiceImpl{}

    @interface ApplicationService{}

    @interface ApplicationCommand{}

    @interface AggregateRoot{}
}
