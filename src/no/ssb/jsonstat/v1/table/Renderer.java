package no.ssb.jsonstat.v1.table;

public interface Renderer<A> {
    A render(Table table);
}
