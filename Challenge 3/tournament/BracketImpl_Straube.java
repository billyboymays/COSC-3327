package tournament;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BracketImpl_Straube<P> extends BracketAbstract<P>
{
	public BracketImpl_Straube(List<P> participantMatchups)
	{
		super(participantMatchups);
	}

	public int getMaxLevel()
	{
		//returns the maximum number of levels given the size of the predictions array
		return (int)((Math.log((predictions.size()/2) + 1))/Math.log(2));
	}

	public Set<Set<P>> getGroupings(int level)
	{
		//This method is too long
		assert 0 <= level;
		assert level <= getMaxLevel();

		//Creation of the return set
		Set<Set<P>> groupings = new HashSet<Set<P>>();
		Set<P> lilgroupings = new HashSet<P>();
		Set<P> multiGroup = new HashSet<P>();
		//Creates and returns a set of size = to the number of participants. Each member will be in their own set.
		if(level == 0)
		{
			for(int i = 0; i < predictions.size(); i++)
			{
				Set<P> groupingToAdd = addLilGroupings(predictions.get(i));
				groupings.add(groupingToAdd);
			}
			return groupings;
		}
		//Creates and returns a set of size 1 with all members in a single set.
		if(level == getMaxLevel())
		{
			for(int i = 0; i < predictions.size(); i++)
			{
				lilgroupings.add(predictions.get(i));
			}
			groupings.add(lilgroupings);
			return groupings;
		}
		//Finds out how many to grab from level zero at a time to add to a set to then add to another set
		int howManyToGrab = (int)Math.pow(2, level);
		//Finds out how many groups need to be formed to allow iteration
		int howManyGroups = getGroupCount(level);
		int currentlyGrabbing = getFirstIndexOfLevel0();
		//Creates and returns a set of size x where x = 2^level in a single set
		for(int chop = 0; chop < howManyGroups; chop++)
		{
			//allows grabbed to be reset every chop
			int grabbed = 0;
			//does the actual places howManyToGrab into a set and increases currentlyGrabbing
			while(grabbed < howManyToGrab)
			{
				//places the first value into the set
				multiGroup.add(predictions.get(currentlyGrabbing));
				//increments
				currentlyGrabbing++;
				grabbed++;
			}
			//places the set into the return set
			groupings.add(multiGroup);
			multiGroup = new HashSet<P>();
		}
		return groupings;
	}

	public Set<P> getViableParticipants(Set<P> grouping)
	{
		//makes sure that the grouping is a correct grouping
		boolean found = false;
		for (int i = 0; i < getMaxLevel(); i++) 
		{
			//basically create all possible groupings and compare it to the passed in set
			if (getGroupings(i).contains(grouping)) 
			{
				found = true;
			}
		}
		assert found;
		//copy the set
		Set<P> cloneGrouping = new HashSet<P>(grouping);
		//Iterate through the grouping pulling each individual member 'team"
		for(P member : grouping)
		{
			boolean lost = false;
			//set parent to be the parent at level 1 since startingIndex(member) finds the member at level 0
			int parent = getParentIndex(startingIndex(member));
			int level = 0;
			//if the team has not lost or we are not beyond the scope of the grouping continue to check if they have lost
			while(!lost && level < getLevel(grouping))
			{
				//if someone else is their parent and the parent is no null then they lost and should be removed from the copy set
				if ((predictions.get(parent) != member) && (predictions.get(parent) != null))
				{
					//remove the loser
					cloneGrouping.remove(member);
					//set the boolean
					lost = true;
				}
				//go up a level with respect to the child
				parent = getParentIndex(parent);
				level++;
			}
		}
		
		return cloneGrouping;	
	}

	public void setWinCount(P participant, int winCount)
	{
		assert participant != null;
		int nextRound = 0;
		//winner is initially the first index of the participant at level 0
		int winner = getParticipantIndex(participant);
		assert winner != -1;
		//Goes until count and winCount are equal
		for(int count = 0; count < winCount; count++)
		{
			//Gets the parent index of the winner which is going one level up
			nextRound = getParentIndex(winner);
			//sets the participant into the array at the parent index position
			predictions.set(nextRound, participant);
			//sets the winner to the value of the parent
			winner = nextRound;
		}
		//'look' above the winner and check if they are themselves if so make it null
		lookUpWinner(nextRound, participant, winCount);
	}

	private int getParticipantIndex(P member)
	{
		//finds the first time a member is found in the array. Used exclusively for setWinCount(P participant, int winCount)
		assert predictions.contains(member);
		for(int i = predictions.size()-1; i > 0; i--)
		{
			//just a basic find
			if(predictions.get(i) == member)
			{
				return i;
				
			}
		}
		//if this is returned then the assert in setWinCount will trip
		return -1;
	}

	private static int getParentIndex(int childIndex)
	{
		//takes an index in the array and finds the value above them "parent"
		assert childIndex != 0;
		//math required to return correctly
		return (((childIndex + 1)/2)-1);
	}

	private Set<P> addLilGroupings(P member)
	{
		//allows for getGroupings(int) to create new sets for level 0
		Set<P> lilgroupings = new HashSet<P>();
		lilgroupings.add(member);
		return lilgroupings;
		//Honestly could have just put this in the method itself but....
	}

	private int getGroupCount(int level)
	{
		//this is big yikes for hard coding. Tried to find algorithm
		//Closest attempt: N/A
		//
		//
		//Just looks at the total levels and breaks it into the amount of sets getGroupings must create
		//up to 64 members
		if(getMaxLevel() == 4)
		{
			if(level == 1)
			{
				return 8;
			}
			else if(level == 2)
			{
				return 4;
			}
			else if(level == 3)
			{
				return 2;
			}
		}
		if(getMaxLevel() == 5)
		{
			if(level == 1)
			{
				return 16;
			}
			else if(level == 2)
			{
				return 8;
			}
			else if(level == 3)
			{
				return 4;
			}
			else if(level == 4)
			{
				return 2;
			}
		}
		if(getMaxLevel() == 6)
		{
			if(level == 1)
			{
				return 32;
			}
			else if(level == 2)
			{
				return 16;
			}
			else if(level == 3)
			{
				return 8;
			}
			else if(level == 4)
			{
				return 4;
			}
			else if(level == 5)
			{
				return 2;
			}
		}
		return -1;
	}

	private int getFirstIndexOfLevel0()
	{
		//Finds level 0 in any array
		return (predictions.size()/2);
	}

	private void lookUpWinner(int nextRound, P member, int winCount)
	{
		//continuation of setWinner if the member above me is myself then make it null.
		if(predictions.get(nextRound) != member || nextRound < 0)
		{
			predictions.set(nextRound, null);
		}
		//This was a lot harder than expected
	}
	
    private int startingIndex(P member)
    {
    	//finds where in level 0 the member originally starts
        assert (predictions.contains(member));
        int goToLevel0 = getFirstIndexOfLevel0();
        int index = 0;
        //continues through the entirety of level 0
        for (int i = goToLevel0; i < predictions.size(); i++) 
        {
        	//just a basic find
            if (predictions.get(i) == member)
            {
                index = i;
            }
        }
        return index;
    }
	
    private int getLevel(Set<P> grouping) 
    {
		//returns the level that grouping size belongs too
		return (int)(Math.log(grouping.size())/Math.log(2));
	}
    
}






