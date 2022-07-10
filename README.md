# Lamport
Synchronized queue. It's my work of 2010 about a Lamport article but I think it is always actual never less the synctactic expression. 

# ref

Time, Clocks and the Ordering of Events in a Distributed System
Communications of the ACM 21, 7   (July 1978), 558-565.  Reprinted in several collections, including Distributed Computing: Concepts and Implementations, McEntire et al., ed.  IEEE Press, 1984.
http://lamport.azurewebsites.net/pubs/time-clocks.pdf

Lamport wrote about this article "
Jim Gray once told me that he had heard two different opinions of this paper: that it's trivial and that it's brilliant.  I can't argue with the former, and I am disinclined to argue with the latter. 
The origin of this paper was the note The Maintenance of Duplicate Databases by Paul Johnson and Bob Thomas.  I believe their note introduced the idea of using message timestamps in a distributed algorithm.  I happen to have a solid, visceral understanding of special relativity (see [5]).  This enabled me to grasp immediately the essence of what they were trying to do.  Special relativity teaches us that there is no invariant total ordering of events in space-time; different observers can disagree about which of two events happened first.  There is only a partial order in which an event e1 precedes an event e2 iff e1 can causally affect e2.  I realized that the essence of Johnson and Thomas's algorithm was the use of timestamps to provide a total ordering of events that was consistent with the causal order.  This realization may have been brilliant.  Having realized it, everything else was trivial.  Because Thomas and Johnson didn't understand exactly what they were doing, they didn't get the algorithm quite right; their algorithm permitted anomalous behavior that essentially violated causality.  I quickly wrote a short note pointing this out and correcting the algorithm. 

It didn't take me long to realize that an algorithm for totally ordering events could be used to implement any distributed system.  A distributed system can be described as a particular sequential state machine that is implemented with a network of processors.  The ability to totally order the input requests leads immediately to an algorithm to implement an arbitrary state machine by a network of processors, and hence to implement any distributed system.  So, I wrote this paper, which is about how to implement an arbitrary distributed state machine. As an illustration, I used the simplest example of a distributed system I could think of--a distributed mutual exclusion algorithm. 

This is my most often cited paper.  Many computer scientists claim to have read it.  But I have rarely encountered anyone who was aware that the paper said anything about state machines.  People seem to think that it is about either the causality relation on events in a distributed system, or the distributed mutual exclusion problem.  People have insisted that there is nothing about state machines in the paper.  I've even had to go back and reread it to convince myself that I really did remember what I had written. 

The paper describes the synchronization of logical clocks.  As something of an afterthought, I decided to see what kind of synchronization it provided for real-time clocks.  So, I included a theorem about real-time synchronization.  I was rather surprised by how difficult the proof turned out to be.  This was an indication of what lay ahead in [62]. "

This paper won the 2000 PODC Influential Paper Award (later renamed the Edsger W. Dijkstra Prize in Distributed Computing).  It won an ACM SIGOPS Hall of Fame Award in 2007. 

https://en.m.wikipedia.org/wiki/Lamport_timestamp
