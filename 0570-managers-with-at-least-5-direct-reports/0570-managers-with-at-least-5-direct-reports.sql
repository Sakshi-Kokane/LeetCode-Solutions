# Write your MySQL query statement below
SELECT name FROM Employee WHERE id 
        IN(
            SELECT managerId FROM Employee
            WHERE managerId is NOT NULL
            GROUP BY managerId HAVING 
            COUNT(*)>=5
    );