package uk.co.caeldev.base.auth2.builders;

import uk.co.caeldev.base.auth2.persisters.Persister;

public interface Builder<T> {

    T build();

    T persist(Persister persister);
}
