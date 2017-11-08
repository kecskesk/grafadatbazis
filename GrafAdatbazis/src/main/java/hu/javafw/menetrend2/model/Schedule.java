package hu.javafw.menetrend2.model;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Schedule entity: contains a scheduled entry  with start/end date and stations.
 *
 * @author kkrisz
 */
@Component
public class Schedule {

    private int id;
    private int originId;
    private int destinationId;
    private Station origin;
    private Station destination;
    private Date startTime;
    private Date endTime;

    public Schedule() {
    }

    public Schedule(int originId, int destinationId, Date startTime, Date endTime) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.startTime = startTime;
        this.endTime = endTime;
    } 

    public Schedule(Station origin, Station destination, Date startTime, Date endTime) {
        this.originId = origin.getId();
        this.destinationId = destination.getId();
        this.origin = origin;
        this.destination = destination;
        this.startTime = startTime;
        this.endTime = endTime;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Schedule other = (Schedule) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
