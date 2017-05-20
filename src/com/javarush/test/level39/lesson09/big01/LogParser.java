package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery
{
    private Path logDir;
    private List<LogEntity> entities = new ArrayList<>();


    public LogParser(Path logDir)
    {
        this.logDir = logDir;
        fillEntitiesList();
    }


    private void fillEntitiesList()
    {
        try
        {
            String[] logs = logDir.toFile().list(new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String name)
                {
                    return name.endsWith(".log");
                }
            });

            List<String> list = new ArrayList<>();
            for (String file : logs)
            {
                try
                {
                    list.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            String[] split;

            for (String line : list)
            {
                split = line.split("\\p{Space}");
                String ip = split[0];
                String name = split[1];
                for (int i = 2; i < split.length; i++)
                {
                    if (split[i].matches("\\w+")) name = name + " " + split[i];
                    else break;
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String dd = "";
                for (int i = 2; i < split.length; i++)
                {
                    if (split[i].matches("\\d+\\.\\d+\\.\\d+"))
                    {
                        dd = split[i] + " " + split[i + 1];
                        break;
                    }
                }
                Date date = dateFormat.parse(dd);
                int task = 0;
                Event event;
                if (split[split.length - 2].matches("\\d+"))
                {
                    event = Event.valueOf(split[split.length - 3]);
                    task = Integer.parseInt(split[split.length - 2]);
                } else event = Event.valueOf(split[split.length - 2]);
                Status status = Status.valueOf(split[split.length - 1]);
                LogEntity entity = new LogEntity(ip, name, date, event, status);
                entity.setTask(task);
                entities.add(entity);
            }
        }
        catch (ParseException e)
        {

        }

    }

    private List<LogEntity> findLogsBetweenDates(Date after, Date before)
    {
        List<LogEntity> logEntities = new ArrayList<>();
        if (entities == null || entities.isEmpty()) return logEntities;
        for (LogEntity entity : entities)
        {
            if (isRightDate(entity.getDate(), after, before))
            {
                logEntities.add(entity);
            }
        }
        return logEntities;
    }

    private boolean isRightDate(Date loggedDate, Date after, Date before)
    {
        if (after == null && before == null)
        {
            return true;
        } else if (after == null)
        {
            if (loggedDate.getTime() <= before.getTime())
            {
                return true;
            }
        } else if (before == null)
        {
            if (loggedDate.getTime() >= after.getTime())
            {
                return true;
            }
        } else
        {
            if (loggedDate.getTime() >= after.getTime() && loggedDate.getTime() <= before.getTime())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            set.add(entity.getIp());
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;

        for (LogEntity entity : logEntities)
        {
            if (entity.getName().equalsIgnoreCase(user)) set.add(entity.getIp());
        }

        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == event) set.add(entity.getIp());
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getStatus() == status) set.add(entity.getIp());
        }
        return set;
    }

    @Override
    public Set<String> getAllUsers()
    {
        Set<String> set = new HashSet<>();
        if (entities == null || entities.isEmpty()) return set;
        for (LogEntity entity : entities)
        {
            set.add(entity.getName());
        }
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return 0;
        Set<String> set = new HashSet<>();
        for (LogEntity entity : logEntities)
        {
            set.add(entity.getName());
        }
        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return 0;
        Set<Event> set = new HashSet<>();
        for (LogEntity logEntity : logEntities)
        {
            if (logEntity.getName().equals(user)) set.add(logEntity.getEvent());
        }

        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getIp().equals(ip)) set.add(entity.getName());
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.LOGIN) set.add(entity.getName());
        }
        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.DOWNLOAD_PLUGIN) set.add(entity.getName());
        }
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.WRITE_MESSAGE) set.add(entity.getName());
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.SOLVE_TASK) set.add(entity.getName());
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.SOLVE_TASK)
            {
                if (entity.getTask() == task)
                {
                    set.add(entity.getName());
                }
            }
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.DONE_TASK) set.add(entity.getName());
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<String> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.DONE_TASK)
            {
                if (entity.getTask() == task)
                {
                    set.add(entity.getName());
                }
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Date> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getName().equals(user) && entity.getEvent() == event)
            {
                set.add(entity.getDate());
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Date> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getStatus() == Status.FAILED) set.add(entity.getDate());
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Date> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getStatus() == Status.ERROR) set.add(entity.getDate());
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return null;
        Date date = new Date(Long.MAX_VALUE);
        boolean isChanged = false;
        for (LogEntity entity : logEntities)
        {
            if (entity.getName().equals(user) && entity.getEvent() == Event.LOGIN)
            {
                if (date.getTime() > entity.getDate().getTime())
                {
                    date = entity.getDate();
                    isChanged = true;
                }
            }
        }

        return isChanged ? date : null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return null;
        Date date = null;
        for (LogEntity entity : logEntities)
        {
            if (entity.getName().equals(user) && entity.getEvent() == Event.SOLVE_TASK && entity.getTask() == task)
            {
                date = entity.getDate();
                break;
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return null;
        Date date = null;
        for (LogEntity entity : logEntities)
        {
            if (entity.getName().equals(user) && entity.getEvent() == Event.DONE_TASK && entity.getTask() == task)
            {
                date = entity.getDate();
                break;
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Date> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getName().equals(user) && entity.getEvent() == Event.WRITE_MESSAGE) set.add(entity.getDate());
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Date> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (user.equals(entity.getName()) && entity.getEvent() == Event.DOWNLOAD_PLUGIN) set.add(entity.getDate());
        }
        return set;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before)
    {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Event> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            set.add(entity.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Event> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getIp().equals(ip)) set.add(entity.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Event> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getName().equals(user)) set.add(entity.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Event> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getStatus() == Status.FAILED) set.add(entity.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before)
    {
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        Set<Event> set = new HashSet<>();
        if (logEntities == null || logEntities.isEmpty()) return set;
        for (LogEntity entity : logEntities)
        {
            if (entity.getStatus() == Status.ERROR) set.add(entity.getEvent());
        }
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return count;

        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.SOLVE_TASK && entity.getTask() == task) count++;
        }
        return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
    {
        int count = 0;
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return count;

        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.DONE_TASK && entity.getTask() == task) count++;
        }
        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> map = new HashMap<>();
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return map;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.SOLVE_TASK)
            {
                map.put(entity.getTask(), getNumberOfAttemptToSolveTask(entity.getTask(), after, before));
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> map = new HashMap<>();
        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
        if (logEntities == null || logEntities.isEmpty()) return map;
        for (LogEntity entity : logEntities)
        {
            if (entity.getEvent() == Event.DONE_TASK)
            {
                map.put(entity.getTask(), getNumberOfSuccessfulAttemptToSolveTask(entity.getTask(), after, before));
            }
        }
        return map;
    }

    @Override
    public Set<Object> execute(String query)
    {
        Set<Object> set = new HashSet<>();
        if (query.matches("\\w+ \\w+"))
        {
            switch (query)
            {
                case "get ip":
                    Set<String> ips = getUniqueIPs(null, null);
                    if (ips == null || ips.isEmpty()) break;
                    for (String ip : ips)
                    {
                        set.add(ip);
                    }
                    break;
                case "get user":
                    Set<String> users = getAllUsers();
                    if (users == null || users.isEmpty()) break;
                    for (String user : users)
                    {
                        set.add(user);
                    }
                    break;
                case "get date":
                    List<LogEntity> entities = findLogsBetweenDates(null, null);
                    if (entities == null || entities.isEmpty()) break;
                    for (LogEntity entity : entities)
                    {
                        set.add(entity.getDate());
                    }
                    break;
                case "get event":
                    Set<Event> events = getAllEvents(null, null);
                    if (events == null || events.isEmpty()) break;
                    for (Event event : events)
                    {
                        set.add(event);
                    }
                    break;
                case "get status":
                    entities = findLogsBetweenDates(null, null);
                    if (entities == null || entities.isEmpty()) break;
                    for (LogEntity entity : entities)
                    {
                        set.add(entity.getStatus());
                    }
                    break;

            }
            return set;
        } else if (query.contains("get") && query.contains(" for ") && query.contains(" and date between "))
        {
            String value2 = query.split("\"")[3];
            String value3 = query.split("\"")[5];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            try
            {
                Date date1 = dateFormat.parse(value2);
                Date date2 = dateFormat.parse(value3);

                return pattern(query, date1, date2);
            }
            catch (ParseException e)
            {
                return set;
            }

        }
        else if (query.contains("get ") && query.contains(" for "))
        {
            return pattern(query, null, null);
        }
        return set;
    }

    private Set<Object> pattern(String query, Date after, Date before)
    {
        Set<Object> set = new HashSet<>();
        String field1 = query.split(" ")[1];
        String field2 = query.split(" ")[3];
        String value1 = query.split("\"")[1];

        switch (field1)
        {
            case "ip":
                switch (field2)
                {
                    case "ip":
                        break;
                    case "user":
                        Set<String> ips = getIPsForUser(value1, after, before);
                        if (ips == null || ips.isEmpty()) break;
                        for (String ip : ips)
                        {
                            set.add(ip);
                        }
                        break;
                    case "date":
                        try
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            Date date = dateFormat.parse(value1);
                            List<LogEntity> logEntities = findLogsBetweenDates(after, before);
                            if (logEntities == null || logEntities.isEmpty()) break;
                            for (LogEntity entity : logEntities)
                            {
                                if (entity.getDate().getTime() == date.getTime()) set.add(entity.getIp());
                            }
                        }
                        catch (ParseException e)
                        {
                            break;
                        }
                        break;
                    case "event":
                        ips = getIPsForEvent(Event.valueOf(value1), after, before);
                        if (ips == null || ips.isEmpty()) break;
                        for (String ip : ips)
                        {
                            set.add(ip);
                        }
                        break;
                    case "status":
                        ips = getIPsForStatus(Status.valueOf(value1), after, before);
                        if (ips == null || ips.isEmpty()) break;
                        for (String ip : ips)
                        {
                            set.add(ip);
                        }
                        break;
                }
                break;
            case "user":
                switch (field2)
                {
                    case "ip":
                        Set<String> users = getUsersForIP(value1, after, before);
                        if (users == null || users.isEmpty()) break;
                        for (String user : users)
                        {
                            set.add(user);
                        }
                        break;
                    case "user":
                        break;
                    case "date":
                        try
                        {
                            List<LogEntity> logEntities = findLogsBetweenDates(after, before);
                            if (logEntities == null || logEntities.isEmpty()) break;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            Date date = dateFormat.parse(value1);
                            for (LogEntity entity : logEntities)
                            {
                                if (entity.getDate().getTime() == date.getTime()) set.add(entity.getName());
                            }
                            break;
                        }
                        catch (ParseException e)
                        {
                            break;
                        }

                    case "event":
                        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities)
                        {
                            if (entity.getEvent() == Event.valueOf(value1)) set.add(entity.getName());
                        }
                        break;
                    case "status":
                        logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities)
                        {
                            if (entity.getStatus() == Status.valueOf(value1)) set.add(entity.getName());
                        }
                        break;
                }
                break;
            case "date":
                switch (field2)
                {
                    case "ip":
                        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities)
                        {
                            if (entity.getIp().equals(value1)) set.add(entity.getDate());
                        }
                        break;
                    case "user":
                        logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities)
                        {
                            if (entity.getName().equals(value1)) set.add(entity.getDate());
                        }
                        break;
                    case "date":
                        break;
                    case "event":
                        logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities)
                        {
                            if (entity.getEvent() == Event.valueOf(value1)) set.add(entity.getDate());
                        }
                        break;
                    case "status":
                        logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities)
                        {
                            if (entity.getStatus() == Status.valueOf(value1)) set.add(entity.getDate());
                        }
                        break;

                }
                break;
            case "event":
                switch (field2) {
                    case "ip":
                        Set<Event> events = getEventsForIP(value1, after, before);
                        if (events == null || events.isEmpty()) break;
                        for (Event event : events) {
                            set.add(event);
                        }
                        break;
                    case "user":
                        events = getEventsForUser(value1, after, before);
                        if (events == null || events.isEmpty()) break;
                        for (Event event : events) {
                            set.add(event);
                        }
                        break;
                    case "date":
                        try
                        {
                            List<LogEntity> logEntities = findLogsBetweenDates(after, before);
                            if (logEntities == null || logEntities.isEmpty()) break;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            Date date = dateFormat.parse(value1);
                            for (LogEntity entity : logEntities)
                            {
                                if (entity.getDate().getTime() == date.getTime()) set.add(entity.getEvent());
                            }
                            break;
                        }
                        catch (ParseException e)
                        {
                            break;
                        }
                    case "event": break;
                    case "status":
                        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities) {
                            if (entity.getStatus() == Status.valueOf(value1)) set.add(entity.getEvent());
                        }
                        break;
                }
                break;
            case "status":
                switch (field2) {
                    case "ip":
                        List<LogEntity> logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities) {
                            if (entity.getIp().equals(value1)) set.add(entity.getStatus());
                        }
                        break;
                    case "user":
                        logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities) {
                            if (entity.getName().equals(value1)) set.add(entity.getStatus());
                        }
                        break;
                    case "date":
                        try
                        {
                            logEntities = findLogsBetweenDates(after, before);
                            if (logEntities == null || logEntities.isEmpty()) break;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            Date date = dateFormat.parse(value1);
                            for (LogEntity entity : logEntities)
                            {
                                if (entity.getDate().compareTo(date) == 0) set.add(entity.getStatus());
                            }
                            break;
                        }
                        catch (ParseException e)
                        {
                            break;
                        }
                    case "event":
                        logEntities = findLogsBetweenDates(after, before);
                        if (logEntities == null || logEntities.isEmpty()) break;
                        for (LogEntity entity : logEntities) {
                            if (entity.getEvent() == Event.valueOf(value1)) set.add(entity.getEvent());
                        }
                        break;
                    case "status": break;


                }
                break;
        }
        return set;
    }


    private class LogEntity
    {
        private String ip;
        private String name;
        private Date date;
        private Event event;
        private Status status;
        private int task = 0;

        public LogEntity(String ip, String name, Date date, Event event, Status status)
        {
            this.ip = ip;
            this.name = name;
            this.date = date;
            this.event = event;
            this.status = status;
        }

        public String getIp()
        {
            return ip;
        }

        public String getName()
        {
            return name;
        }

        public Date getDate()
        {
            return date;
        }

        public Event getEvent()
        {
            return event;
        }

        public Status getStatus()
        {
            return status;
        }

        public void setTask(int task)
        {
            this.task = task;
        }

        public int getTask()
        {
            return task;
        }
    }
}

