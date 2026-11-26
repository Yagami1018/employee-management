package cu.uho.workers.model;

import java.io.Serializable;

// @formatter:off
public abstract sealed class Worker implements Serializable permits Laborer, Technician, Manager {
// @formatter:on

}
