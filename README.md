#Flood Buster

By using a `play.cache.CacheImpl` implementation, this project allows you to detect abuses at your system by storing any action with its key and returns the remaining actions allowed withing the interval.

### Basic Usage

```
CacheImpl myCache;
RateLimitManager manage = new RateLimitManager();
manage.setCache(myCache);
int remaining = manage.getAllowedActionCount(key, timeout, limit); 
```

The timeout is in milliseconds and limit indicates how many actions that the specific key has left.

### Logic
Firstly, the action counter is set with a timestamp. Within the given timeout interval, the counter is reduced in each cache hit. The remaining count is always returned to the user. If the remaining count is less than 0, it means that action is abused. Otherwise, the client should understand user is not abusing. 

The action is reset to initial limit value after timeout period.