package com.example.animelist.network;

public final class Queries {

    public final static String ANIME_LIST = "query ($season: MediaSeason, $year: Int, $format: MediaFormat, $excludeFormat: MediaFormat, $status: MediaStatus, $minEpisodes: Int, $page: Int, $perPage:Int, $sort: [MediaSort], $search:String) {\n" +
            "  Page(page: $page, perPage: $perPage) {\n" +
            "    pageInfo {\n" +
            "      total\n" +
            "      currentPage\n" +
            "      hasNextPage\n" +
            "    }\n" +
            "    media(season: $season, seasonYear: $year, format: $format, format_not: $excludeFormat, status: $status, episodes_greater: $minEpisodes, isAdult: false, type: ANIME, sort: $sort, search: $search) {\n" +
            "      id\n" +
            "      title {\n" +
            "        romaji\n" +
            "      }\n" +
            "      startDate {\n" +
            "        year\n" +
            "        month\n" +
            "        day\n" +
            "      }\n" +
            "      endDate {\n" +
            "        year\n" +
            "        month\n" +
            "        day\n" +
            "      }\n" +
            "      status\n" +
            "      season\n" +
            "      format\n" +
            "      synonyms\n" +
            "      duration\n" +
            "      popularity\n" +
            "      episodes\n" +
            "      averageScore\n" +
            "      description\n" +
            "      bannerImage\n" +
            "      coverImage {\n" +
            "        large\n" +
            "      }\n" +
            "      rankings {\n" +
            "        year\n" +
            "        rank\n" +
            "        type\n" +
            "        season\n" +
            "        allTime\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}\n";

    public final static String ANIME = "query ($id: Int, $type: MediaType) {\n" +
            "  Media(id: $id, type: $type) {\n" +
            "    id\n" +
            "    title {\n" +
            "      romaji\n" +
            "    }\n" +
            "    startDate {\n" +
            "      year\n" +
            "      month\n" +
            "      day\n" +
            "    }\n" +
            "    endDate {\n" +
            "      year\n" +
            "      month\n" +
            "      day\n" +
            "    }\n" +
            "    status\n" +
            "    season\n" +
            "    format\n" +
            "    synonyms\n" +
            "    duration\n" +
            "    popularity\n" +
            "    episodes\n" +
            "    averageScore\n" +
            "    description\n" +
            "    bannerImage\n" +
            "    coverImage {\n" +
            "      large\n" +
            "    }\n" +
            "    rankings {\n" +
            "      year\n" +
            "      rank\n" +
            "      type\n" +
            "      season\n" +
            "      allTime\n" +
            "    }\n" +
            "    streamingEpisodes {\n" +
            "      title\n" +
            "      thumbnail\n" +
            "      url\n" +
            "    }\n" +
            "    mediaListEntry{\n" +
            "      id\n" +
            "      mediaId\n" +
            "      status\n" +
            "      score(format:POINT_100)\n" +
            "      progress\n" +
            "      startedAt {\n" +
            "        year\n" +
            "        month\n" +
            "        day\n" +
            "      }\n" +
            "      completedAt {\n" +
            "        year\n" +
            "        month\n" +
            "        day\n" +
            "      }\n" +
            "    }\n" +
            "    characters(sort: [ROLE, RELEVANCE, ID]) {\n" +
            "      pageInfo {\n" +
            "        total\n" +
            "        currentPage\n" +
            "        hasNextPage\n" +
            "      }\n" +
            "      edges {\n" +
            "        id\n" +
            "        role\n" +
            "        name\n" +
            "        node {\n" +
            "          id\n" +
            "          name {\n" +
            "            full\n" +
            "          }\n" +
            "          image {\n" +
            "            large\n" +
            "          }\n" +
            "          description\n" +
            "          gender\n" +
            "          age\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "  \n" +
            "}\n";

    public static final String USER = "query {\n" +
                "  Viewer {\n" +
                "    id\n" +
                "    name\n" +
                "    about\n" +
                "    avatar {\n" +
                "      large\n" +
                "    }\n" +
                "    bannerImage\n" +
                "    createdAt\n" +
                "    statistics {\n" +
                "      anime {\n" +
                "        count\n" +
                "        meanScore\n" +
                "        standardDeviation\n" +
                "        minutesWatched\n" +
                "        episodesWatched\n" +
                "        statuses(sort: [COUNT_DESC]) {\n" +
                "          count\n" +
                "          meanScore\n" +
                "          minutesWatched\n" +
                "          status\n" +
                "        }\n" +
                "        startYears{\n" +
                "          count\n" +
                "          meanScore\n" +
                "          minutesWatched\n" +
                "          startYear\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

    public static final String UPDATE_ANIME_LIST_ENTRY = "mutation($id: Int, $mediaId: Int, $status: MediaListStatus, $scoreRaw: Int, $progress: Int, $startedAt: FuzzyDateInput, $completedAt: FuzzyDateInput) {\n" +
            "  SaveMediaListEntry(id: $id, mediaId: $mediaId, status: $status, scoreRaw: $scoreRaw, progress: $progress, startedAt: $startedAt, completedAt: $completedAt) {\n" +
            "    id\n" +
            "    mediaId\n" +
            "    status\n" +
            "    score(format: POINT_10)\n" +
            "    progress\n" +
            "    startedAt {\n" +
            "      year\n" +
            "      month\n" +
            "      day\n" +
            "    }\n" +
            "    completedAt {\n" +
            "      year\n" +
            "      month\n" +
            "      day\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public static final String ANIME_LIST_COLLECTION = "query($userId: Int, $sort: [MediaListSort]) {\n" +
            "  MediaListCollection(userId: $userId, sort: $sort, type: ANIME) {\n" +
            "    lists {\n" +
            "      name\n" +
            "      status\n" +
            "      entries {\n" +
            "        id\n" +
            "        mediaId\n" +
            "        status\n" +
            "        score(format: POINT_10)\n" +
            "        progress\n" +
            "        startedAt {\n" +
            "          year\n" +
            "          month\n" +
            "          day\n" +
            "        }\n" +
            "        completedAt {\n" +
            "          year\n" +
            "          month\n" +
            "          day\n" +
            "        }\n" +
            "        media {\n" +
            "          id\n" +
            "          title {\n" +
            "            romaji\n" +
            "          }\n" +
            "          coverImage {\n" +
            "            large\n" +
            "          }\n" +
            "          episodes\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
    public static final String CHARACTER = "query character($id: Int) {\n" +
            "  Character(id: $id) {\n" +
            "    id\n" +
            "    name {\n" +
            "      full\n" +
            "    }\n" +
            "    image {\n" +
            "      large\n" +
            "    }\n" +
            "    description\n" +
            "    gender\n" +
            "    age\n" +
            "  }\n" +
            "}\n";
}
