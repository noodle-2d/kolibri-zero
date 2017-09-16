package com.ran.kolibri.service

import com.ran.kolibri.entity.other.Comment
import com.ran.kolibri.entity.other.CommentsHolder
import com.ran.kolibri.exception.BadRequestException
import com.ran.kolibri.extension.logInfo
import com.ran.kolibri.repository.other.CommentRepository
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService {

    companion object {
        private val LOGGER = Logger.getLogger(CommentService::class.java)
    }

    @Autowired
    lateinit var commentRepository: CommentRepository

    fun <T : CommentsHolder> addComment(commentsHolder: T,
                                        holderRepository: CrudRepository<T, Long>,
                                        text: String) {
        LOGGER.logInfo { "Adding a comment: text = $text" }
        if (text.trim().isEmpty()) {
            LOGGER.logInfo { "Text is empty, comment is not added" }
            return
        }

        val comment = Comment()
        comment.text = text
        comment.date = Date()
        commentRepository.save(comment)

        commentsHolder.comments.add(comment)
        holderRepository.save(commentsHolder)
        LOGGER.logInfo { "Comment was added" }
    }

    fun <T : CommentsHolder> editComment(commentsHolder: T,
                                         commentIndex: Int,
                                         text: String) {
        if (text.trim().isEmpty()) {
            return
        }

        if (commentIndex !in 0..(commentsHolder.comments.size - 1)) {
            throw BadRequestException("Comment does not exist")
        }

        val comment = commentsHolder.comments[commentIndex]
        comment.text = text
        commentRepository.save(comment)
    }

    fun <T : CommentsHolder> removeComment(commentsHolder: T,
                                           holderRepository: CrudRepository<T, Long>,
                                           commentIndex: Int) {
        if (commentIndex !in 0..(commentsHolder.comments.size - 1)) {
            throw BadRequestException("Comment does not exist")
        }

        val oldComments = commentsHolder.comments
        commentRepository.delete(oldComments)

        commentsHolder.comments.clear()
        oldComments.forEachIndexed { index, comment ->
            if (index != commentIndex) {
                commentsHolder.comments.add(comment.clone())
            }
        }
        holderRepository.save(commentsHolder)
    }

}
