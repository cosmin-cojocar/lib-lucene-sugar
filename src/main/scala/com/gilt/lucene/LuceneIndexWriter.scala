package com.gilt.lucene

import org.apache.lucene.index.{IndexWriter, IndexWriterConfig}
import javax.annotation.Nonnull

/**
 * Provides a method to write to the index
 */
trait LuceneIndexWriter { self: LuceneDirectory with LuceneAnalyzerProvider with LuceneVersion =>

  /**
   * Calls the passed function with an IndexWriter that writes to the current index.
   * Makes sure to close the IndexWriter once the function returns.
   */
  @Nonnull
  def withIndexWriter[T](@Nonnull f: IndexWriter => T): T = {
    val iwriter = new IndexWriter(directory, new IndexWriterConfig(luceneVersion, luceneAnalyzer))
    try {
      f(iwriter)
    } finally {
      iwriter.close()
    }
  }

}
